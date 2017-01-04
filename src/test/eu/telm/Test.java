
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Test
    public void contextLoads() {
    }
    @Test
    public void testowaniePacjenta() {
        Patient patient1= new Patient();
        patient1.setImie("Michael");
        assertEquals("3","4");
    }

}