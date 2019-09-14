package io.ashimjk.part2;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
class Money extends ValueObject<Money> {

    public static Money NONE = new Money(0, 0, 0, 0, 0, 0);
    public static Money CENT = new Money(1, 0, 0, 0, 0, 0);
    public static Money TENCENT = new Money(0, 1, 0, 0, 0, 0);
    public static Money QUARTER = new Money(0, 0, 1, 0, 0, 0);
    public static Money DOLLAR = new Money(0, 0, 0, 1, 0, 0);
    public static Money FIVEDOLLAR = new Money(0, 0, 0, 0, 1, 0);
    public static Money TWENTYDOLLAR = new Money(0, 0, 0, 0, 0, 1);

    private int oneCentCount;
    private int tenCentCount;
    private int quarterCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;

    Money(
            int oneCentCount,
            int tenCentCount,
            int quarterCount,
            int oneDollarCount,
            int fiveDollarCount,
            int twentyDollarCount
    ) {
        if (oneCentCount < 0)
            throw new InvalidArgumentException();
        if (tenCentCount < 0)
            throw new InvalidArgumentException();
        if (quarterCount < 0)
            throw new InvalidArgumentException();
        if (oneDollarCount < 0)
            throw new InvalidArgumentException();
        if (fiveDollarCount < 0)
            throw new InvalidArgumentException();
        if (twentyDollarCount < 0)
            throw new InvalidArgumentException();

        this.oneCentCount = oneCentCount;
        this.tenCentCount = tenCentCount;
        this.quarterCount = quarterCount;
        this.oneDollarCount = oneDollarCount;
        this.fiveDollarCount = fiveDollarCount;
        this.twentyDollarCount = twentyDollarCount;
    }

    static Money add(Money money1, Money money2) {
        return new Money(
                money1.oneCentCount + money2.oneCentCount,
                money1.tenCentCount + money2.tenCentCount,
                money1.quarterCount + money2.quarterCount,
                money1.oneDollarCount + money2.oneDollarCount,
                money1.fiveDollarCount + money2.fiveDollarCount,
                money1.twentyDollarCount + money2.twentyDollarCount
        );
    }

    static Money minus(Money money1, Money money2) {
        return new Money(
                money1.oneCentCount - money2.oneCentCount,
                money1.tenCentCount - money2.tenCentCount,
                money1.quarterCount - money2.quarterCount,
                money1.oneDollarCount - money2.oneDollarCount,
                money1.fiveDollarCount - money2.fiveDollarCount,
                money1.twentyDollarCount - money2.twentyDollarCount
        );
    }

    static Money multiply(Money money1, int multiplier) {
        return new Money(
                money1.oneCentCount * multiplier,
                money1.tenCentCount * multiplier,
                money1.quarterCount * multiplier,
                money1.oneDollarCount * multiplier,
                money1.fiveDollarCount * multiplier,
                money1.twentyDollarCount * multiplier);
    }

    @Override
    public boolean equalsCore(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return oneCentCount == money.oneCentCount &&
                tenCentCount == money.tenCentCount &&
                quarterCount == money.quarterCount &&
                oneDollarCount == money.oneDollarCount &&
                fiveDollarCount == money.fiveDollarCount &&
                twentyDollarCount == money.twentyDollarCount;
    }

    @Override
    public int hashCodeCore() {
        return Objects.hash(
                oneCentCount,
                tenCentCount,
                quarterCount,
                oneDollarCount,
                fiveDollarCount,
                twentyDollarCount
        );
    }

    @Override
    public String toString() {
        if (getAmount() < 1)
            return "¢" + (getAmount() * 100);

        return "$" + getAmount();
    }

    boolean canAllocate(double amount) {
        Money money = allocateCore(amount);
        return money.getAmount() == amount;
    }

    Money allocate(double amount) {
        if (!canAllocate(amount))
            throw new InvalidArgumentException();

        return allocateCore(amount);
    }

    private Money allocateCore(double amount) {
        int twentyDollarCount = Math.min((int) (amount / 20), this.twentyDollarCount);
        amount = amount - twentyDollarCount * 20;

        int fiveDollarCount = Math.min((int) (amount / 5), this.fiveDollarCount);
        amount = amount - fiveDollarCount * 5;

        int oneDollarCount = Math.min((int) amount, this.oneDollarCount);
        amount = amount - oneDollarCount;

        int quarterCount = Math.min((int) (amount / 0.25), this.quarterCount);
        amount = amount - quarterCount * 0.25;

        int tenCentCount = Math.min((int) (amount / 0.1), this.tenCentCount);
        amount = amount - tenCentCount * 0.1;

        int oneCentCount = Math.min((int) (amount / 0.01), this.oneCentCount);

        return new Money(
                oneCentCount,
                tenCentCount,
                quarterCount,
                oneDollarCount,
                fiveDollarCount,
                twentyDollarCount);
    }

    double getAmount() {
        return BigDecimal.valueOf(
                oneCentCount * 0.01 +
                        tenCentCount * 0.10 +
                        quarterCount * 0.25 +
                        oneDollarCount +
                        fiveDollarCount * 5 +
                        twentyDollarCount * 20
        )
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
