package ru.nsk.pavlov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        double intersectionFrom = Math.max(from, range.from);
        double intersectionTo = Math.min(to, range.to);

        if (intersectionFrom < intersectionTo) {
            return new Range(intersectionFrom, intersectionTo);
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (to < range.from || range.to < from) {
            return new Range[]{
                    new Range(Math.min(from, range.from), Math.min(to, range.to)),
                    new Range(Math.max(from, range.from), Math.max(to, range.to))
            };
        }

        double unionFrom = Math.min(from, range.from);
        double unionTo = Math.max(to, range.to);

        return new Range[]{new Range(unionFrom, unionTo)};
    }

    public Range[] getDifference(Range range) {
        if (to <= range.from || from >= range.to) {
            return new Range[]{new Range(from, to)};
        }

        if (range.from <= from && range.to >= to) {
            return new Range[0];
        }

        if (from < range.from && to > range.to) {
            return new Range[]{
                    new Range(from, range.from),
                    new Range(range.to, to)
            };
        }

        if (from < range.from) {
            return new Range[]{new Range(from, range.from)};
        }

        if (to > range.to) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[0];
    }

    public String toString() {
        return "(" + from + ", " + to + ")";
    }
}