//package org.zeep.library.repo;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.zeep.library.model.BookByYear;
//import org.zeep.library.model.BookItemModel;
//
//import java.util.Arrays;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.lenient;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class BookYearRepoTest {
//
//    BookItemModel b = BookItemModel.builder().id(UUID.randomUUID()).build();
//    BookItemModel b1 = BookItemModel.builder().id(UUID.randomUUID()).build();
//
//    @Mock
//    BookYearRepo repo;
//    private final BookByYear year = BookByYear.builder().id(1L).year("1992").books(
//            Arrays.asList(b, b1)
//    ).build();
//
//    @BeforeEach
//    void setUp() {
//        lenient().when(repo.findByYear("1992")).thenReturn(year);
//    }
//
//    @Test
//    void findByYear() {
//        BookByYear y = repo.findByYear("1992");
//        assertNotNull(y);
//        assertEquals(2, y.getBooks().size());
//    }
//}