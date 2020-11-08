package car.racing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CarRacingManager {

    private static final int LIMIT = 10;

    private final Forwardable forwardable;
    private final List<Car> cars;
    private final Random random = new Random();

    public CarRacingManager(String[] names, Forwardable forwardable) {
        this.cars = Arrays.stream(names)
                .map(Car::new)
                .collect(Collectors.toList());
        this.forwardable = forwardable;
    }

    public void forwardCarEachTry() {
        cars.stream()
                .filter(car -> forwardable.forwardable(random.nextInt(LIMIT)))
                .forEach(Car::forward);
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<String> racingWinners() {
        Car carMostFast = getCarsInFastOrder().get(0);
        return getCarsInFastOrder().stream()
                .filter(car -> car.getForwardCount() == carMostFast.getForwardCount())
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private List<Car> getCarsInFastOrder() {
        return cars.stream()
                .sorted(Comparator.comparing(Car::getForwardCount).reversed())
                .collect(Collectors.toList());
    }
}