package service;

import dao.*;
import dto.*;

public class CountryService {
    private CountryDao countryDao = new CountryDaoMySql();

    public Country getCountryByCountryId(int id) {

        return countryDao.getCountryByCountryId(id);
    }

}
