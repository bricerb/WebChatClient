package tiy.project;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebChatClientSpringApplicationTests {
    Server myServer = new Server();

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void sendMessage() throws Exception {
		String message = "Test-String";
        Client myClient = new Client();


		String serverResponse = myClient.sendMessage(message);

		System.out.println(serverResponse);

		assertEquals(message, serverResponse);
	}

}