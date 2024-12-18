package exercise;

import java.util.stream.IntStream;

// BEGIN
class ReversedSequence implements CharSequence{
    private final String intString;

    public ReversedSequence(String newStr) {
        StringBuilder str  = new StringBuilder(newStr);
        this.intString = str.reverse().toString();
    }
    @Override
    public int length() {
        return intString.length();
    }

    @Override
    public char charAt(int index) {
        return intString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return intString.subSequence(start, end);
    }

    @Override
    public String toString() {
        return intString;
    }
}

// END
