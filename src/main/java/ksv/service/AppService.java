package ksv.service;

import ksv.exceptions.IncorrectlyDataException;
import ksv.view.View;

import java.io.*;

public class AppService implements ServiceInterface {
    View view;
    String[] pattern = {"string", "string", "string", "data", "number", "gender"};

    public AppService(View view) {
        this.view = view;
    }


    public AppService(View view, String pattern) {
        this.view = view;
        this.pattern = pattern.trim().split(",");
    }


    @Override
    public void save(String[] str) {
        String toSave = "";
        try (BufferedReader br = new BufferedReader(new FileReader(str[0]))) {
            String tmp;
            while ((tmp = br.readLine()) != null) {
                toSave += tmp + "\n";
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        for (int i = 0; i < str.length; i++) {
            toSave += "<" + str[i] + ">";
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(str[0]))) {
            bw.write(toSave);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] parse(String str) throws IncorrectlyDataException {
        String[] strings = str.trim().split(" ");
        if (check(strings)) {
            return strings;
        }
        return null;
    }

    @Override
    public boolean check(String[] strings) throws IncorrectlyDataException {
        if (strings.length != pattern.length) {
            throw new IncorrectlyDataException("неверное количество данных");
        }
        for (int i = 0; i < pattern.length; i++) {
            switch (pattern[i]) {
                case "string" -> {
                }
                case "data" -> {
                    String pat = "1234567890.";
                    String[] tmp;
                    try {
                        tmp = strings[i].split("\\.");
                        if (tmp[0].length() != 2 || tmp[1].length() != 2 || tmp[2].length() != 4 || tmp.length != 3) {
                            throw new IncorrectlyDataException("дата");
                        }
                        for (int j = 0; j < tmp.length; j++) {
                            Integer.parseInt(tmp[j]);
                        }
                    } catch (Exception e) {
                        throw new IncorrectlyDataException("дата");
                    }

                    for (char c : strings[i].toCharArray()) {
                        if (!pat.contains(String.valueOf(c))) {
                            throw new IncorrectlyDataException("дата");
                        }
                    }
                }
                case "number" -> {
                    try {
                        Integer.parseInt(strings[i]);
                    } catch (NumberFormatException e) {
                        throw new IncorrectlyDataException("телефонный номер");
                    }
                }
                case "gender" -> {
                    if ((strings[i].equals("f")) || (strings[i].equals("m"))) {
                    } else {
                        throw new IncorrectlyDataException("пол");
                    }
                }
                default -> {
                    throw new IncorrectlyDataException();
                }
            }
        }
        return true;
    }
}
