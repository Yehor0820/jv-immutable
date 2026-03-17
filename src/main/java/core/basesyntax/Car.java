package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Car {
    private final int year;
    private final String color;
    private final List<Wheel> wheels;
    private final Engine engine;

    public Car(int year, String color, List<Wheel> wheels, Engine engine) {
        this.year = year;
        this.color = color;
        this.engine = engine == null ? null : engine.clone();
        this.wheels = wheels != null ? cloneWheels(wheels) : throwNpeForWheels();
    }

    private List<Wheel> cloneWheels(List<Wheel> wheels) {
        List<Wheel> copy = new ArrayList<>();
        for (Wheel wheel : wheels) {
            copy.add(wheel != null ? wheel.clone() : null);
        }
        return copy;
    }

    private List<Wheel> throwNpeForWheels() {
        throw new NullPointerException("Wheels не можуть бути null");
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public Engine getEngine() {
        return engine == null ? null : engine.clone();
    }

    public List<Wheel> getWheels() {
        List<Wheel> copy = new ArrayList<>();
        for (Wheel wheel : wheels) {
            copy.add(wheel.clone());
        }
        return copy;
    }

    public Car changeEngine(Engine newEngine) {
        return new Car(
                year,
                color,
                wheels,
                Objects.requireNonNull(newEngine, "Engine не може бути null")
        );
    }

    public Car changeColor(String newColor) {
        return new Car(
                year,
                Objects.requireNonNull(newColor, "Color не може бути null"),
                wheels,
                engine
        );
    }

    public Car addWheel(Wheel newWheel) {
        Objects.requireNonNull(newWheel, "Wheel не може бути null");
        List<Wheel> newWheels = new ArrayList<>(wheels);
        newWheels.add(newWheel.clone());
        return new Car(year, color, newWheels, engine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return year == car.year
                && Objects.equals(color, car.color)
                && Objects.equals(wheels, car.wheels)
                && Objects.equals(engine, car.engine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, color, wheels, engine);
    }

    @Override
    public String toString() {
        return "Car{"
                + "year=" + year
                + ", color='" + color + '\''
                + ", wheels=" + wheels
                + ", engine=" + engine
                + '}';
    }
}
