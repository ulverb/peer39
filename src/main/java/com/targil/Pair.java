package com.targil;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pair<L, R> {
    private L left;
    private R right;

    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

    public <R1> Pair<L, R1> newRight(R1 right) {
        return new Pair<>(left, right);
    }

    public <L1> Pair<L1, R> newLeft(L1 left) {
        return new Pair<>(left, right);
    }
}
