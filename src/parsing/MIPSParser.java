package parsing;

import Declarations.MIPSDeclaration;
import constants.Constants;
import utils.DeclarationUtil;
import utils.InstructionUtil;
import utils.PseudoinstructionUtil;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MIPSParser {

    public HashMap<String, Integer> labels;
    public HashMap<String, MIPSDeclaration> declarations;

    public List<String> data;
    public List<String> text;

    public MIPSParser() {
        this.data = new ArrayList<>();
        this.text = new ArrayList<>();
    }

    public void parseASMFile(List<String> input) {
        List<String[]> tokenizedData = new ArrayList<>();
        List<String[]> tokenizedText = new ArrayList<>();

        input.removeIf(line -> line.trim().isEmpty());
        splitASMFile(input);

        for (String declaration : data) {
            tokenizedData.add(parseDeclaration(declaration));
        }

        // String[] hexData = MIPSDeclarationManager.getDeclaration("ok").toHex("test");

        //
        //


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

    public static String[] parseInstruction(String input) {
        input = input.replaceAll(",", "").toLowerCase();
        input = input.replace("(", " ").replace(")", " ").trim();
        return input.split("#")[0].split("\\s+");
    }

    public static String[] parseDeclaration(String input) {
        input = input.trim();
        return input.split("\\s+", 3);
    }

}
