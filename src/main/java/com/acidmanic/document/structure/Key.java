/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.document.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author diego
 */
public class Key {

    private final List<String> segments;
    private final boolean caseSensitive;

    public Key() {
        this(false);
    }

    public Key(boolean caseSensitive, String... segments) {

        this.segments = new ArrayList<>();

        this.segments.addAll(Arrays.asList(segments));

        this.caseSensitive = caseSensitive;
    }

    public Key(String... segments) {
        this(false, segments);
    }

    public Key(Key key) {

        this(key.caseSensitive);

        key.segments.forEach(esgment -> this.segments.add(esgment));
    }

    public void append(Key key) {

        this.segments.addAll(segments);
    }

    public void append(String... segments) {

        for (String segment : segments) {

            this.segments.add(segment);
        }
    }

    public int segmentsCount() {

        return this.segments.size();
    }

    public boolean startsWith(Key key) {

        if (key.segments.size() > this.segments.size()) {
            return false;
        }
        for (int i = 0; i < key.segments.size(); i++) {

            if (!areEqual(this.segments.get(i), key.segments.get(i), this.caseSensitive)) {
                return false;
            }
        }
        return true;
    }

    private boolean areEqual(String first, String second, boolean caseSensitive) {
        if (caseSensitive) {
            return first.equals(second);
        } else {
            return first.equalsIgnoreCase(second);
        }
    }

    public String uniqueHash() {

        StringBuilder sb = new StringBuilder();
        String sep = "";

        for (String segment : this.segments) {

            if (!caseSensitive) {
                segment = segment.toLowerCase();
            }

            sb.append(sep).append(segment);

            sep = "::>";
        }
        return sb.toString();
    }

    public String segment(int segmentIndex) {

        return this.segments.get(segmentIndex);
    }

    public Key subKey(int start, int end) {

        Key key = new Key(this.caseSensitive);

        for (int i = start; i < end; i++) {
            key.segments.add(this.segments.get(i));
        }
        return key;
    }

    public String leafValue() {
        if (!this.segments.isEmpty()) {
            return this.segment(this.segments.size() - 1);
        }
        return "";
    }

    public String jointSegments() {
        return jointSegments(";");
    }

    public String jointSegments(String delimiter) {

        String sep = "";
        String joint = "";

        for (String segment : this.segments) {

            joint += sep + segment;

            sep = delimiter;
        }
        return joint;
    }

    public boolean pointsToRoot() {
        return this.segments.isEmpty();
    }

    @Override
    public String toString() {
        return jointSegments("/");
    }

}
