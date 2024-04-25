package itmo.st.io;

import itmo.st.math.approx.ApproxResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class CsvSaver {

    private CsvSaver() {

    }

    public static void saveApproxResultToCsv(ApproxResult approxResult) {
        var date = new Date();
        String fileName = approxResult.functionName() + "-" + date + ".csv";
        fileName = fileName.replace(" ", "_");
        fileName = fileName.replace(":", "-");

        saveApproxResultToCsv(approxResult, fileName);
    }


    public static void saveApproxResultToCsv(ApproxResult approxResult, String fileName) {
        StringBuilder sb = new StringBuilder();
        for (String x : approxResult.results().keySet()) {
            sb.append(x).append(",").append(approxResult.results().get(x)).append("\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results/" + fileName))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
