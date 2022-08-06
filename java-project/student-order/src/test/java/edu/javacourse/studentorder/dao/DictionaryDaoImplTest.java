package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class DictionaryDaoImplTest {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryDaoImplTest.class);

    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Test
    public void testStreet() throws DaoException {
        logger.info("testStreet()");
        List<Street> streets = new DictionaryDaoImpl().findStreets("про");
        Assert.assertEquals(2, streets.size());
    }

    @Test
    public void testPassportOffice() throws DaoException {
        logger.debug("testPassportOffice()");
        List<PassportOffice> passportOffices = new DictionaryDaoImpl().findPassportOffices("010020000000");
        Assert.assertEquals(2, passportOffices.size());
    }

    @Test
    public void testRegisterOffice() throws DaoException {
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now();
        logger.info("testRegisterOffice: {} {}", dt1, dt2); // passing parameter

        List<RegisterOffice> registerOffices = new DictionaryDaoImpl().findRegisterOffices("010010000000");
        Assert.assertEquals(2, registerOffices.size());
    }

    @Test
    public void testArea() throws DaoException{
        List<CountryArea> ca1 = new DictionaryDaoImpl().findAreas("");
        Assert.assertEquals(2, ca1.size());
        List<CountryArea> ca2 = new DictionaryDaoImpl().findAreas("020000000000");
        Assert.assertEquals(2, ca2.size());
        List<CountryArea> ca3 = new DictionaryDaoImpl().findAreas("020010000000");
        Assert.assertEquals(2, ca3.size());
        List<CountryArea> ca4 = new DictionaryDaoImpl().findAreas("020010010000");
        Assert.assertEquals(2, ca4.size());
    }
}