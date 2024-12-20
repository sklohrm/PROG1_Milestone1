package parsing;

import Declarations.MIPSDeclaration;
import constants.Constants;
import managers.MIPSInstructionManager;
import utils.DeclarationUtil;
import utils.InstructionUtil;
import utils.PseudoinstructionUtil;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MIPSParser {

    public HashMap<String, String> labels;

    public List<String> data;
    public List<String> text;

    public MIPSParser() {
        this.data = new ArrayList<>();
        this.text = new ArrayList<>();
        this.labels = new HashMap<>();
    }

    public void parseASMFile(List<String> input) {
        List<String[]> tokenizedData = new ArrayList<>();
        List<String[]> tokenizedText = new ArrayList<>();

        input.removeIf(line -> line.trim().isEmpty());

        splitASMFile(input);



        tokenizedData = parseData();
        refactorData(tokenizedData);

        tokenizedText = parseText();

        //Create address variable
        int memAddr = 0;
        //Loop and convert pseudoinstructions
        for (int i = 0; i < tokenizedText.size(); ++i) {
            if (determineLineType(tokenizedText.get(i)[0]) == Constants.LineType.PSEUDOINSTRUCTION) {
                List<String[]> instructions = convertPseudoInstruction(tokenizedText.get(i));
                tokenizedText.remove(i);
                for (int j = 0; j < instructions.size(); ++j) {
                    tokenizedText.add(i + j, instructions.get(j));
                }
            }
        }

        //Loop to save and remove labels
        for (int i = 0; i < tokenizedText.size(); ++i) {
            if (determineLineType(tokenizedText.get(i)[0]) == Constants.LineType.LABEL) {
                labels.put(tokenizedText.get(i)[0].substring(0, tokenizedText.get(i)[0].length() - 1), Integer.toHexString(Constants.TEXT_SEG_START + memAddr));
                tokenizedText.remove(i--);
            }
            else memAddr += 4;
        }

        //Second loop to replace label references with value from labels map
        memAddr = 0;
        for (String[] line : tokenizedText) {
            for (int i = 0; i < line.length; ++i) {
                if (labels.containsKey(line[i])) {
                    if (line[0].equals("beq")) {
                        int target = Integer.decode("0x" + labels.get(line[i]));
                        int next = memAddr + 4 + Constants.TEXT_SEG_START;
                        int destination = (target - next) / 4;
                        line[3] = String.valueOf(destination);
                    } else if (line[0].equals("j")) {
                        int target = Integer.decode("0x" + labels.get(line[i])) >> 2;
                        line[1] = String.valueOf(target);
                    } else {
                        line[i] = "0x" + labels.get(line[i]);
                    }

                }
            }
            memAddr += 4;
        }

        text.clear();
        for (String[] line : tokenizedText) {
            text.add(MIPSInstructionManager.getInstruction(line[0]).toHex(line));
        }

    }


    //TODO
    private void refactorData(List<String[]> tokenizedData) {
        //Global memory address memAddr
        int memAddr = 0;

        //Stringbuilder to hold the text file string
        StringBuilder textSb = new StringBuilder();

        //Create local string to hold the 8 hexcode
        String eightChars = "";

        //Counter to know when 4th character is hit
        int count = 0;

        //Go through Tokenized data to get Strings
        for(String[] lines : tokenizedData) {
            //Get the prompt to turn to small endian
            String prompt = lines[2];

            //Add label to map
            labels.put(lines[0], Integer.toHexString(Constants.DATA_SEG_START + memAddr));

            //Add length of prompt for memory address
            memAddr += prompt.length() + 1;

            //Loop through prompt starting after quotation and ending before it
            for (int i = 0; i < prompt.length(); ++i) {
                //Add the hex of the letter to the front of the String to reverse it


                if(prompt.charAt(i) == '\\' && i+1 < prompt.length()){

                    if (Constants.BACKSLASH_CONVENTIONS.containsKey(String.valueOf(prompt.charAt(i+1)))) {
                        eightChars = Constants.BACKSLASH_CONVENTIONS.get(String.valueOf(prompt.charAt(i+1))) + eightChars;
                        i++;
                    }

                    else eightChars = Integer.toHexString((int) prompt.charAt(i)) + eightChars;
                }

                else eightChars = Integer.toHexString((int) prompt.charAt(i)) + eightChars;


                //If we got 4 char string in reverse order
                if (++count == 4) {
                    //Reset counter
                    count = 0;

                    //Add the reverse 8 chars to the final String
                    textSb.append(eightChars);

                    //Clear eightChars
                    eightChars = "";
                }
            }

            //Add null character
            eightChars = "00" + eightChars;
            ++count;
        }

        //If count is not 0, finish 8 char string
        if (count != 0) {
            //Add 00 until count reaches 4
            while (count ++ != 4) {
                eightChars = "00" + eightChars;
            }
            //Add the final reverse 8 chars to final String
            textSb.append(eightChars);
        }

        data.clear();
        for (int i = 0; i < textSb.length(); i+=8) {
            data.add(textSb.substring(i, i + 8));
        }
    }

    private void textToHex(List<String[]> tokenizedText) {

    }

//    private List<String[]> convertPseudoinstructions(List<String[]> tokenizedText) {
//        List<String[]> temp = new ArrayList<>();
//        for (String[] line : tokenizedText) {
//            if (PseudoinstructionUtil.isPseudoInstruction(line[0])) {
//                temp.addAll(convertPseudoInstruction(line));
//            } else {
//                temp.add(line);
//            }
//        }
//        return temp;
//    }

    private List<String[]> convertPseudoInstruction(String[] input) {
        List<String[]> output = new ArrayList<>();
        switch (input[0]) {
            case "la":
                int imm;
                if (labels.containsKey(input[2])) {
                    imm = Integer.decode("0x" + labels.get(input[2]));
                } else {
                    imm = Integer.parseInt(input[2]);
                }
                if (imm >= -32768 && imm <= 32767) {
                    output.add(new String[]{"addiu", input[1], "$zero", String.valueOf(imm)});
                } else {
                    output.add(new String[]{"lui", "$at", String.valueOf((imm >>> 16) & 0xFFFF)});
                    output.add(new String[]{"ori", input[1], "$at",  String.valueOf(imm & 0xFFFF)});
                }
            break;
            case "li":
                imm = Integer.parseInt(input[2]);
                if (imm >= -32768 && Integer.decode(input[2]) <= 32767) {
                    output.add(new String[]{"addiu", input[1], "$zero", String.valueOf(imm)});
                } else {
                    output.add(new String[]{"lui", "$at", String.valueOf((imm >>> 16) & 0xFFFF)});
                    output.add(new String[]{"ori", input[1], "$at", String.valueOf(imm & 0xFFFF)});
                }
            break;
            case "blt":
                output.add(new String[]{"slt", "$at", input[1], input[2]});
                output.add(new String[]{"bne", "$at", "$zero", input[3]});
            break;
            case "move":
                output.add(new String[]{"add", input[1], input[2], "$zero"});
            break;
            default:
            break;
        }
        return output;
    }

    private void splitASMFile(List<String> input) {
        for (String line : input) {
            Constants.LineType lineType = determineLineType(line);
            switch (lineType) {
                case COMMENT, DATA, TEXT:
                    break;
                case LABEL, PSEUDOINSTRUCTION, INSTRUCTION:
                    text.add(line);
                    break;
                case DECLARATION:
                    data.add(line);
                    break;
                default:
                    throw new RuntimeException("Unknown line type: " + lineType);
            }
        }
    }

    private Constants.LineType determineLineType(String line) {
        line = line.trim();

        if (line.charAt(0) == '#') {
            return Constants.LineType.COMMENT;

        } else if (line.equals(".data")) {
            return Constants.LineType.DATA;

        } else if (line.equals(".text")) {
            return Constants.LineType.TEXT;

        } else if (line.charAt(line.length() - 1) == ':') {
            return Constants.LineType.LABEL;

        } else if (DeclarationUtil.isDeclaration(line)) {
            return Constants.LineType.DECLARATION;

        } else if (PseudoinstructionUtil.isPseudoInstruction(line)) {
            return Constants.LineType.PSEUDOINSTRUCTION;

        } else if (InstructionUtil.isInstruction(line)) {
            return Constants.LineType.INSTRUCTION;
        } else {

            throw new IllegalArgumentException("Unknown line type: " + line);
        }
    }

    private List<String[]> parseData() {
        List<String[]> output = new ArrayList<>();
        for (String line : data) {
            output.add(parseDeclaration(line));
        }
        return output;
    }

    private List<String[]> parseText() {
        List<String[]> output = new ArrayList<>();
        for (String line : text) {
            output.add(parseInstruction(line));
        }
        return output;
    }

    public static String[] parseInstruction(String input) {
        input = input.replaceAll(",", "").toLowerCase();
        input = input.replace("(", " ").replace(")", " ").trim();
        return input.split("#")[0].split("\\s+");
    }

    public static String[] parseDeclaration(String input) {
        input = input.trim();
        input = input.replaceFirst(":", "");
        input = input.replaceFirst("\"", "");
        input = input.substring(0, input.length() - 1);
        return input.split("\\s+", 3);
    }

}
