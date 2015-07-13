/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils.colour;

import dynamics.api.Copyable;

public abstract class Colour implements Copyable<Colour> {

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