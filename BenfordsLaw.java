import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BenfordsLaw {
    public static void main(String[] args) {
        String FileName = "your path here";
        String fileContent = readFile(FileName);
        fileContent.replaceAll(" ", "");
        String[] leadingNumbers = fileContent.split("\\.");
        for (int i = 0; i < leadingNumbers.length; i++) {
            leadingNumbers[i] = leadingNumbers[i].trim();
        }
        Map<String, Double> leadingNumberCount = getLeadingNumberCount(leadingNumbers);
        Map<String, Double> leadingNumberPercentages = calculateLeadingNumberPercentage(leadingNumberCount);
        for (String s : leadingNumberPercentages.keySet()) {
            System.out.println(s + " : " + df.format(leadingNumberPercentages.get(s)) + "%");
        }

    }

    public static DecimalFormat df = new DecimalFormat("#.##");

    public static Map<String, Double> getLeadingNumberCount(String[] allLeadingNumbers) {
        Map<String, Double> leadingNumberFrequencies = new HashMap<>();
        for (String leadingNumber : allLeadingNumbers) {
            if (leadingNumberFrequencies.containsKey(leadingNumber)) {
                leadingNumberFrequencies.put(leadingNumber, leadingNumberFrequencies.get(leadingNumber) + 1);
            } else {
                leadingNumberFrequencies.put(leadingNumber, 1.0);
            }
        }
        return leadingNumberFrequencies;
    }

    public static Map<String, Double> calculateLeadingNumberPercentage(Map<String, Double> leadingNumberCount) {
        Map<String, Double> leadingNumberPercentage = new HashMap<>();
        double countSum = 0.0;
        Object[] valuesArray = new Object[leadingNumberCount.values().size()];
        valuesArray = leadingNumberCount.values().toArray();
        for (int i = 0; i < valuesArray.length; i++) {
            countSum += (Double) valuesArray[i];
        }
        for (String s : leadingNumberCount.keySet()) {
            leadingNumberPercentage.put(s, leadingNumberCount.get(s) / Double.valueOf(countSum) * 100);
        }

        return leadingNumberPercentage;
    }

    public static String readFile(String FileName) {
        String fileContent = "";
        try {
            File myObj = new File(FileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                fileContent = fileContent + " " + line;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return fileContent;
    }
}
