package com.tyyna.oil.business.repositories.main;




import io.reactivex.Single;


public class MainRepository implements IMainRepository {


    @Override
    public Single<MovieWrapper> getPopularMovie(int pageNumber) {
        return null;
    }

    @Override
    public Single<MovieWrapper> getTopRated(int pageNumber) {
        return null;
    }
}
