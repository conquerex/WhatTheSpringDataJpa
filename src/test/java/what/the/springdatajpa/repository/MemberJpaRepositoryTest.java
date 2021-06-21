package what.the.springdatajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import what.the.springdatajpa.ExcelPOIHelper;
import what.the.springdatajpa.entity.Member;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// junit5부터 @RunWith를 생략해도 된다
@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(savedMember.getId());

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }

    private ExcelPOIHelper excelPOIHelper;
    private static String FILE_NAME = "temp.xlsx";
    private String fileLocation;

    @BeforeEach
    public void generateExcelFile() throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + FILE_NAME;

        excelPOIHelper = new ExcelPOIHelper();
        excelPOIHelper.writeExcel();
    }

    @Test
    public void whenParsingPOIExcelFile_thenCorrect() throws IOException {
        Map<Integer, List<String>> data = excelPOIHelper.readExcel(fileLocation);

        assertEquals("Name", data.get(0).get(0));
        assertEquals("Age", data.get(0).get(1));

        assertEquals("John Smith", data.get(1).get(0));
        // cell.getDateCellValue()은 double 타입으로 반환하기 때문에 "20"이 아닌 "20.0"으로 출력
        assertEquals("20.0", data.get(1).get(1));
    }
}