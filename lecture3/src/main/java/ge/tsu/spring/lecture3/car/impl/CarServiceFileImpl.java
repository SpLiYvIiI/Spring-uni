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

  private static final String JSON_DATA = "src/main/java/ge/tsu/spring/lecture3/car/data/cars.json";
  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void add(AddCar addCar) throws RecordAlreadyExistsException, IOException {
    List<CarView> carList = objectMapper.readValue(
            new File(JSON_DATA),
            new TypeReference<List<CarView>>(){});
    for(CarView car : carList){
      if(car.getManufacturer().equals(addCar.getManufacturer()) && car.getModel().equals(addCar.getModel())){
        throw new RecordAlreadyExistsException(
                String.format("Car with %s and %s already exists", addCar.getManufacturer(), addCar.getModel()));
      }
    }
    CarView toAdd = new CarView(
            UUID.randomUUID().toString(),
            addCar.getManufacturer(),
            addCar.getModel(),
            addCar.getSpeed()
    );
    carList.add(toAdd);
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_DATA),carList);
  }

  @Override
  public void update(String id, AddCar modifyCar) throws  RecordNotFoundException, IOException {
    List<CarView> carList = objectMapper.readValue(
            new File(JSON_DATA),
            new TypeReference<List<CarView>>() {
            });
    Boolean foundRecord = false;
    CarView carToModify = new CarView();
    for(CarView car : carList){
      System.out.println(car.getId() + "   " + id);
      if(car.getId().equals(id)) {
        foundRecord = true;
        carToModify = car;
        break;
      }
    }
    if(!foundRecord) {
      throw new RecordNotFoundException("Unable to find car with specified id");
    }
    carToModify.setModel(modifyCar.getModel());
    carToModify.setSpeed(modifyCar.getSpeed());
    carToModify.setManufacturer(modifyCar.getManufacturer());
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_DATA),carList);
  }

  @Override
  public List<CarView> getList(String manufacturer, String model) throws IOException {
      List<CarView> carList = objectMapper.readValue(
              new File(JSON_DATA),
              new TypeReference<List<CarView>>() {
              });
    if(manufacturer != null) {
      for(Iterator<CarView> it = carList.iterator(); it.hasNext();)
        if (!(it.next().getManufacturer().contains(manufacturer)))
          it.remove();
    }
    if(model != null) {
      for(Iterator<CarView> it = carList.iterator(); it.hasNext();)
        if (!(it.next().getModel().contains(model)))
          it.remove();
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
    for(Iterator<CarView> it = carList.iterator(); it.hasNext();)
      if (it.next().getId().equals(id)) {
        it.remove();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_DATA),carList);
        return;
      }

    throw new RecordNotFoundException("Unable to find car with specified id");
  }
}
