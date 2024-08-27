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
            "   swimmerAbsences_aggregate {\n" +
            "      aggregate {\n" +
            "        count(columns: entityid)\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}\n"

    const val GET_SWIMMER_BY_ID = """
    query GetSwimmerById(${'$'}swimmerid: uuid!) {
      swimmers(where: {swimmerid: {_eq: ${'$'}swimmerid}}) {
        swimmerid
        firstname
        lastname
        sex
        birthday
        pfpUrl
        ispro
        level {
          levelid
          levelname
        }
        trainer {
          firstname
          lastname
          phonenumber
        }
        swimmerAbsences_aggregate {
          aggregate {
            count(columns: entityid)
          }
        }
      }
    }
"""}