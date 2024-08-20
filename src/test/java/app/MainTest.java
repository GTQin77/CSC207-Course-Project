//package app;
//
//import app.setup.SetupDataAccessAndServices;
//import interface_adapter.ViewManagerModel;
//import org.junit.jupiter.api.Test;
//import view.ViewManager;
//
//import javax.swing.*;
//
//import java.awt.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class MainTest {
//    @Test
//    void testMainFrameSetup() {
//        JFrame frame = Main.setupMainFrame();
//        assertNotNull(frame);
//        assertEquals("Planify", frame.getTitle());
//        assertEquals(WindowConstants.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
//    }
//
//    @Test
//    void testViewPanelSetup() {
//        JFrame mockFrame = mock(JFrame.class);
//        JPanel panel = Main.setupViewPanel(mockFrame);
//        assertNotNull(panel);
//        verify(mockFrame).add(panel, BorderLayout.CENTER);
//    }
//
//    @Test
//    void testInitializeServicesAndViews() {
//        ViewManagerModel model = mock(ViewManagerModel.class);
//        ViewManager manager = mock(ViewManager.class);
//        JPanel views = mock(JPanel.class);
//        SetupDataAccessAndServices setup = mock(SetupDataAccessAndServices.class);
//
//        // Assume SetupDataAccessAndServices is already injected or mockable
//        Main.initializeServicesAndViews(model, manager, views);
//
//
//        // Assertions to verify interactions can be added here
//        // For example, verify that setup.setup was called with expected arguments
//        // This part of the test can be enhanced once the real interactions are well-defined
//    }
//}
