package com.paweloot.gotmobile.utility

import com.paweloot.gotmobile.model.entity.User
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HasherTest {

    private val hashLength = 64

    private val emptyString = ""
    private val longString =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer pharetra sapien turpis, non malesuada nisl molestie et. Vestibulum id ligula quis eros ornare fermentum eget at eros. Curabitur fringilla, mi ut placerat hendrerit, eros elit commodo risus, ut aliquet justo metus et nisl."
    private val veryLongString =
        "Phasellus luctus nibh in convallis blandit. In tristique diam in libero mollis, ac imperdiet orci tristique. Nullam condimentum ante a justo rhoncus, vitae vulputate velit congue. Integer odio sapien, consequat in porttitor ac, aliquam in lorem. Sed nisl magna, consequat et dolor ac, maximus eleifend est. Pellentesque id dolor iaculis, convallis urna ac, rutrum dui. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tincidunt purus eu velit consequat laoreet. Curabitur turpis est, aliquet et massa at, tincidunt tristique mauris. Ut sem neque, ullamcorper non nunc et, maximus mattis ex. Mauris ipsum ante, pellentesque sit amet iaculis at, mollis at arcu. Praesent consectetur accumsan nisi, id lobortis magna maximus eget.\n" +
                "\n" +
                "Nam interdum ut nisi et varius. Aliquam efficitur tortor pellentesque ipsum sagittis ultricies. Fusce tincidunt lobortis ante, in luctus purus rhoncus id. Ut bibendum velit ac diam aliquet, vel blandit odio varius. Etiam ultrices massa quis libero interdum, eleifend lacinia ante fermentum. Ut vehicula, tortor a porta viverra, ex nisl accumsan sem, eu pellentesque lectus diam sed justo. Fusce felis quam, vestibulum at venenatis id, rhoncus scelerisque magna.\n" +
                "\n" +
                "Aenean eget ligula pulvinar, cursus enim eu, condimentum mi. Aenean condimentum consequat dolor, in pellentesque lorem pulvinar at. Aliquam ornare ligula quis urna convallis consequat. Sed interdum in neque ut mattis. Mauris vitae neque eu lorem posuere consectetur. Sed ligula nisl, vestibulum vel efficitur ac, laoreet ac arcu. Pellentesque eleifend dictum arcu, vitae convallis ligula placerat et. Cras tincidunt nulla id egestas dapibus. Sed eget malesuada ipsum. Morbi iaculis erat sit amet purus pellentesque, quis auctor leo eleifend. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas."

    private lateinit var hasher: Hasher
    private lateinit var user1: User
    private lateinit var user2: User
    private lateinit var user3: User

    @Before
    fun setUp() {
        hasher = Hasher()

        user1 = User(
            1, "werSpal@gmail.com", "konikiMorski3lala", "Weronika",
            "Spalina", null
        )
        user2 = User(
            2, "romek@gmail.com", "budapesztWlecie", "Roman",
            "Romańczukiewicz", null
        )
        user3 = User(
            3, "marta@gmail.com", "harcerzeDziecmiLasu", "Marta",
            "Harcerka", null
        )
    }

    @Test
    fun hashCorrect() {
        assertEquals(
            "e15ea7fac70fac63d4682aebcf3b4d92d22a9641fee05106e7975cdc5f098761",
            hasher.hash(user1.password)
        )
        assertEquals(
            "2d06b02bb8a7cdaa02d6314c6669dd6c5a24142863c59949261720febdb451cf",
            hasher.hash(user2.password)
        )
        assertEquals(
            "79636c27b4488f99a475e56113af7c350faf46a1d57475628726b6f61f93e262",
            hasher.hash(user3.password)
        )

        assertEquals(
            "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
            hasher.hash(emptyString)
        )
        assertEquals(
            "3bbfd8bb037e95cce3f1d45499cfe57d80e35bddc45d60c544816e5714a57461",
            hasher.hash(longString)
        )
        assertEquals(
            "71a5dc8798a4eb887fb00fff13b9855015ed4f96e7e1c4fa2c700507134f992b",
            hasher.hash(veryLongString)
        )
    }

    @Test
    fun hashHasCorrectLength() {
        assertEquals(hashLength, hasher.hash(user1.password).length)
        assertEquals(hashLength, hasher.hash(user2.password).length)
        assertEquals(hashLength, hasher.hash(user3.password).length)

        assertEquals(hashLength, hasher.hash(emptyString).length)
        assertEquals(hashLength, hasher.hash(longString).length)
        assertEquals(hashLength, hasher.hash(veryLongString).length)
    }
}