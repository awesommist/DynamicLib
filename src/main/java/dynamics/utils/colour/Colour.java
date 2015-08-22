package dynamics.utils.colour;

import dynamics.api.ICopyable;

public abstract class Colour implements ICopyable<Colour> {

    public byte r;
    public byte g;
    public byte b;
    public byte a;

    public Colour(int r, int g, int b, int a) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
        this.a = (byte) a;
    }

    public Colour(Colour colour) {
        this (colour.r, colour.g, colour.b, colour.a);
    }

    public abstract int pack();

    public abstract Colour copy();
}