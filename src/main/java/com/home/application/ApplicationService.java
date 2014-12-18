package com.home.application;

public interface ApplicationService {
    void addSuffixToEntitiesBeginningWith(String prefix, char ch);

    void deleteEntitiesBeginningWith(char ch);
}
