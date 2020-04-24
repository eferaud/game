/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.utils;

/**
 *
 * @author eric
 */
public class Segment {

    private int begin;
    private int length;

    public Segment(int begin, int length) {
        this.begin = begin;
        this.length = length;
    }

    public int getBegin() {
        return begin;
    }

    public int getLength() {
        return length;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.begin;
        hash = 29 * hash + this.length;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Segment other = (Segment) obj;
        if (this.begin != other.begin) {
            return false;
        }
        if (this.length != other.length) {
            return false;
        }
        return true;
    }

}
