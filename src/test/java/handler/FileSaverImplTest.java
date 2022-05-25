package handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class FileSaverImplTest {


    // I tried but unfortunately I couldn't do it
    @Mock
    private FileHandler fileHandler;

    @InjectMocks
    private FileSaverImpl fileSaver;

    @Test
    void shouldSaveFile() {
        //given
        Mockito.when(fileHandler.getSpreadFile(new ArrayList<>())).thenReturn("This is data to be saved");
        //then
        fileSaver.saveFile();
    }

}