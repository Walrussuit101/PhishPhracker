package actionsTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import actions.EmailSender;

public class EmailSenderTest {
	
	private EmailSender emailSender;
	
	@Before
	public void setUp() {
		emailSender = new EmailSender("tjefferson@ycp.edu");
	}
	
	@Test
	public void testSendShowEmail() {
		int sendShowEmail = emailSender.sendShowEmail("1999-12-31", "A whole setlist");
		assertEquals(sendShowEmail, 0);
	}
}
