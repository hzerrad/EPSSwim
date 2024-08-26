package com.example.epsswim.data.utils

object Queries {
    const val GET_PARENT_SWIMMERS="\n query GetParentSwimmersList {\n" +
            "  swimmers {\n" +
            "    swimmerid\n" +
            "    firstname\n" +
            "    lastname\n" +
            "    birthday\n" +
            "    pfpUrl\n" +
            "    ispro\n" +
            "    level {\n" +
            "      levelid\n" +
            "      levelname\n" +
            "    }\n" +
            "    trainer {\n" +
            "      firstname\n" +
            "      lastname\n" +
            "    }\n" +
            "  }\n" +
            "}\n"
}