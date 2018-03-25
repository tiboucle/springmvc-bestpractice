package com.training;

import static org.assertj.core.api.Assertions.assertThat;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class UserControllerWebIntegrationTests {

  @Autowired
  private MockMvc mvc;

  private WebClient webClient;
  private MockMvcWebConnection mockConnection;

  @Before
  public void setUp() {
    webClient = new WebClient();
    mockConnection = new MockMvcWebConnection(mvc, webClient);
  }

  @Test
  public void testLoadLoginPage() throws IOException {
    HtmlPage page = webClient.getPage("http://127.0.0.1:8092/login");
    assertThat(page).isNotNull();
  }
}
