package com.thoughtworks.training.banking;

import com.thoughtworks.training.banking.common.IdGenerator;
import com.thoughtworks.training.banking.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  private static final String TEMPLATE = "Hello, %s!";

  private final IdGenerator idGenerator;

  @Autowired
  public GreetingController(IdGenerator idGenerator) {
    this.idGenerator = idGenerator;
  }

  @RequestMapping({"/", "/greeting"})
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new Greeting(idGenerator.nextId(), String.format(TEMPLATE, name));
  }

}
