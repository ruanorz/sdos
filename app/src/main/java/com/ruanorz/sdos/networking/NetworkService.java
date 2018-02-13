package com.ruanorz.sdos.networking;



import com.ruanorz.sdos.models.FruitListResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ruano on 20/01/2018.
 */

public interface NetworkService {

    String CATEGORY = "category";
    String ITEM = "item";

    @GET("hma6-9xbg.json")
    Observable<List<FruitListResponse>> getFruitList(
         @Query(CATEGORY) String category,
         @Query(ITEM) String item);

}
