package com.crud.movies.facade;

import com.crud.movies.domain.Movie;
import com.crud.movies.repository.MovieRepository;
import com.crud.movies.domain.User;
import com.crud.movies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchingFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchingFacade.class);


    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public List<Movie> moviesWithTitle (String titleFragment) throws SearchException{
        if (titleFragment.length() == 0) {
            LOGGER.error(SearchException.ERR_TITLE_FRAGMENT_IS_NULL);
            throw new SearchException(SearchException.ERR_TITLE_FRAGMENT_IS_NULL);
        }

        LOGGER.info("SEARCHING FOR MOVIES WITH TITLE CONTAINS: " + titleFragment);

        List<Movie> listOfMoviesFound = movieRepository.moviesWithTitle(titleFragment);
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

        List<User>  listOfUsersFound = userRepository.usersWithName(nameFragment);
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
