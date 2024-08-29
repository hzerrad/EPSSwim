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
"""
    const val GET_LEVELS = """
    query GetTrainerLevel {
      levels {
        levelid
        levelname
      }
    }
"""
    const val GET_TRAINER_BY_ID = """
    query GetTrainerInfo {
      trainers {
        trainerid
        firstname
        lastname
        bloodtype
        birthday
        phonenumber
        pfpUrl
        levels {
          levelid
          levelname
        }
        trainerAbsences {
          absenceid
          absencedate
        }
        trainerAbsences_aggregate {
          aggregate {
            count(columns: entityid)
          }
        }
      }
    }
"""
    const val UPLOAD_TRAINER_PHOTO_PROFILE = """
    mutation UpdateTrainerPfp(${'$'}trainerid: uuid = "", ${'$'}pfpUrl: String = "") {
      update_trainers_by_pk(pk_columns: {trainerid: ${'$'}trainerid}, _set: {pfpUrl: ${'$'}pfpUrl}) {
         pfpUrl
      }  
    }
"""
    const val UPLOAD_SWIMMER_PHOTO_PROFILE = """
    mutation UpdateSwimmerPfp(${'$'}swimmerid: uuid = "", ${'$'}pfpUrl: String = "") {
      update_swimmers_by_pk(pk_columns: {swimmerid: ${'$'}swimmerid}, _set: {pfpUrl: ${'$'}pfpUrl}) {
         pfpUrl
      }  
    }
"""
    const val GET_SWIMMERS_BY_LEVEL_ID = """
    query GetSwimmerByLevelId(${'$'}levelid: uuid!) {
      swimmers(where: {levelid: {_eq: ${'$'}levelid}}) {
        swimmerid
        firstname
        lastname
        birthday
        pfpUrl
        swimmerAbsences_aggregate {
          aggregate {
            count(columns: entityid)
          }
       }
     }
   }
"""
}