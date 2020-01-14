package com.paweloot.gotmobile.api

import com.paweloot.gotmobile.model.entity.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

private const val BASE_URL = "http://192.168.43.130:8080/api/"

/**
 * Interfejs DAO definiujący metody potrzebne aplikacji do komunikacji
 * z odpowiednimi endpoint'ami REST API, z którego korzysta biblioteka
 * Retrofit przy generowaniu odpowiednich klas.
 */
interface GotApi {

    /**
     * Pobiera listę wszystkich pasm górskich.
     *
     * @return lista pasm górskich
     */
    @GET("mtnRanges")
    fun getMtnRanges(): Call<List<MtnRange>>

    /**
     * Pobiera listę wszystkich grup górskich dla danego pasma.
     *
     * @param mtnRangeId identyfikator wybranego pasma górskiego
     * @return lista grup górskich w danym paśmie górskim
     */
    @GET("mtnRanges/{mtnRangeId}/mtnGroups")
    fun getMtnGroups(@Path("mtnRangeId") mtnRangeId: Int):
            Call<List<MtnGroup>>

    /**
     * Pobiera listę wszystkich punktów dla danej grupy górskiej.
     *
     * @param mtnGroupId identyfikator wybranej grupy górskiej
     * @return lista punktów w danej grupie górskiej
     */
    @GET("mtnGroups/{mtnGroupId}/points")
    fun getPoints(@Path("mtnGroupId") mtnGroupId: Int):
            Call<List<Point>>

    /**
     * Pobiera listę punktów, które są osiągalne z wybranego punktu.
     *
     * @param pointFromId identyfikator punktu początkowego
     * @return lista osiągalnych punktów
     */
    @GET("points/pointsFrom/{pointFromId}")
    fun getFilteredPoints(@Path("pointFromId") pointFromId: Int):
            Call<List<Point>>

    /**
     * Pobiera listę odcinków, które zawierają się w trasie tworzonej
     * przez listę dostarczonych punktów.
     *
     * @param pointsIds lista identyfikatorów punktów trasy
     * @return lista odcinków do podsumowania
     */
    @POST("summaryPaths")
    fun getSummaryPaths(@Body pointsIds: List<Int>):
            Call<List<SummaryPath>>

    /**
     * Zapisuje stworzoną trasę w bazie danych.
     *
     * @param postTripBody trasa do zapisania
     * @return zapisana trasa zwrócona przez REST API
     */
    @POST("trips")
    fun saveTrip(@Body postTripBody: PostTripBody):
            Call<Trip>

    /**
     * Autoryzuje użytkownika w systemie.
     *
     * @param email email użytkownika
     * @param encodedPassword hasło użytownika po hashowaniu (SHA-256)
     * @return dane zalogowanego użytkownika
     */
    @GET("tourists/authorize/{email}/{encodedPassword}")
    fun authorizeTourist(
        @Path("email") email: String,
        @Path("encodedPassword") encodedPassword: String
    ): Call<Tourist>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Singleton przechowujący obiekt do komunikacji z REST API.
 */
object RetrofitApi {

    /**
     * Obiekt wygenerowany przez bibliotekę Retrofit, służący do
     * komunikacji z REST API.
     */
    val gotApi: GotApi by lazy {
        retrofit.create(GotApi::class.java)
    }
}