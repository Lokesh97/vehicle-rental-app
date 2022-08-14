package com.navi.vehiclerental.models;

import com.navi.vehiclerental.exceptions.InvalidCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    private static final String SPACE = " ";
    private String name;
    private List<String> params;

    public Command(String inputLine) {
        final List<String> tokensList = Arrays.stream(inputLine.trim().split(SPACE))
                .map(String::trim)
                .filter(token -> (token.length() > 0)).collect(Collectors.toList());

        if (tokensList.size() == 0) {
            throw new InvalidCommandException();
        }

        this.name = tokensList.get(0);
        tokensList.remove(0);
        this.params = tokensList;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getParams() {
        return this.params;
    }
}
