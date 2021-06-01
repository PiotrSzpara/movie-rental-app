package com.crud.movies.facade;

import com.crud.movies.domain.Movie;
import com.crud.movies.domain.MovieDao;
import com.crud.movies.domain.User;
import com.crud.movies.domain.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchingFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchingFacade.class);

    @Autowired
    MovieDao movieDao;

    @Autowired
    UserDao userDao;

    public List<Movie> moviesWithTitle (String titleFragment) throws SearchException{
        if (titleFragment.length() == 0) {
            LOGGER.error(SearchException.ERR_TITLE_FRAGMENT_IS_NULL);
            throw new SearchException(SearchException.ERR_TITLE_FRAGMENT_IS_NULL);
        }

        LOGGER.info("SEARCHING FOR MOVIES WITH TITLE CONTAINS: " + titleFragment);

        List<Movie> listOfMoviesFound = movieDao.moviesWithTitle(titleFragment);
        if(listOfMoviesFound.isEmpty()) {
            LOGGER.info("NO MOVIES FOUND WHERE TITLE CONTAINS: " + titleFragment);
        }
        for(Movie movie : listOfMoviesFound) {
            LOGGER.info("MOVIE THAT MATCH THE CRITERIA: " + movie.getMovieTitle());
        }
        LOGGER.info("SEARCHING PROCESS ENDED");

        return listOfMoviesFound;
    }

    public List<User> usersWithName (String nameFragment) throws SearchException{
        if (nameFragment.length() == 0) {
            LOGGER.error(SearchException.ERR_NAME_FRAGMENT_IS_NULL);
            throw new SearchException(SearchException.ERR_NAME_FRAGMENT_IS_NULL);
        }

        LOGGER.info("SEARCHING FOR USERS WITH NAME CONTAINS: " + nameFragment);

        List<User>  listOfUsersFound = userDao.usersWithName(nameFragment);
        if(listOfUsersFound.isEmpty()) {
            LOGGER.info("NO USERS FOUND WHERE NAME CONTAINS: " + nameFragment);
        }
        for(User user : listOfUsersFound) {
            LOGGER.info("USER THAT MATCH THE CRITERIA: " + user.getUserName());
        }
        LOGGER.info("SEARCHING PROCESS ENDED");

        return listOfUsersFound;
    }



}
