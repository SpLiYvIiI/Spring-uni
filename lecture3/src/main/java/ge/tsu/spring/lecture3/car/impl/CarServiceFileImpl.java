package ge.tsu.spring.lecture3.car.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.tsu.spring.lecture3.car.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service("carFileImpl")
public class CarServiceFileImpl implements CarService {

  private static final String JSON_DATA = "lecture3/src/main/java/ge/tsu/spring/lecture3/car/data/cars.json";
  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void add(AddCar addCar) throws RecordAlreadyExistsException, IOException {
    List<CarView> carList = objectMapper.readValue(
            new File(JSON_DATA),
            new TypeReference<List<CarView>>(){});
    Optional<CarView> exists = carList
            .stream()
            .filter(it -> addCar.getManufacturer().equals(it.getManufacturer()) && it.getModel().equals(addCar.getModel()))
            .findFirst();
    if (exists.isPresent()) {
      throw new RecordAlreadyExistsException(
              String.format("Car with %s and %s already exists", addCar.getManufacturer(), addCar.getModel()));
    }
    carList.add(new CarView(
            UUID.randomUUID().toString(),
            addCar.getManufacturer(),
            addCar.getModel(),
            addCar.getSpeed()
    ));
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_DATA),carList);
  }

  @Override
  public void update(String id, AddCar addCar) throws  RecordNotFoundException, IOException {
    List<CarView> carList = objectMapper.readValue(
            new File(JSON_DATA),
            new TypeReference<List<CarView>>() {
            });
    for (CarView carView : carList) {
      if (carView.getId().equals(id)) {
        carView.setManufacturer(addCar.getManufacturer());
        carView.setModel(addCar.getModel());
        carView.setSpeed(addCar.getSpeed());
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_DATA),carList);
        return;
      }
    }
    throw new RecordNotFoundException("Unable to find car with specified id");
  }

  @Override
  public List<CarView> getList(String manufacturer, String model) throws IOException {
      List<CarView> carList = objectMapper.readValue(
              new File(JSON_DATA),
              new TypeReference<List<CarView>>() {
              });

    if (manufacturer != null && model != null) {
      return carList
              .stream()
              .filter(it -> it.getManufacturer().contains(manufacturer) && it.getModel().contains(model))
              .collect(Collectors.toList());
    }
    return carList;
  }

  @Override
  public CarView getById(String id) throws RecordNotFoundException , IOException {
    List<CarView> carList = objectMapper.readValue(
            new File(JSON_DATA),
            new TypeReference<List<CarView>>() {});
    for (CarView carView : carList) {
      if (carView.getId().equals(id)) {
        return carView;
      }
    }
    throw new RecordNotFoundException("Unable to find car with specified id");
  }

  @Override
  public void delete(String id) throws RecordNotFoundException, IOException{
    List<CarView> carList = objectMapper.readValue(
            new File(JSON_DATA),
            new TypeReference<List<CarView>>() {});
    Iterator<CarView> carViewIterator = carList.iterator();
    while (carViewIterator.hasNext()) {
      CarView carView = carViewIterator.next();
      if (carView.getId().equals(id)) {
        carViewIterator.remove();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_DATA),carList);
        return;
      }
    }
    throw new RecordNotFoundException("Unable to find car with specified id");
  }
}
