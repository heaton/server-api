package com.thoughtworks.training.banking;

import com.thoughtworks.training.banking.common.IdGenerator;
import com.thoughtworks.training.banking.model.Greeting;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GreetingControllerSpec {

  @Mock
  private IdGenerator idGenerator;

  @InjectMocks
  private GreetingController greetingController;

  @Before
  public void given_id_always_be_8() {
    when(idGenerator.nextId()).thenReturn(8L);
  }

  @Test
  public void should_get_a_greeting() {
    assertThat(greetingController.greeting("Heaton"), is(new Greeting(8, "Hello, Heaton!")));
  }

}
