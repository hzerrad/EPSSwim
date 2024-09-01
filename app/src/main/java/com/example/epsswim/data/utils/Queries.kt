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
    query GetSwimmerByLevelId(${'$'}levelid: uuid!, ${'$'}absencedate: date = "") {
      swimmers(where: {levelid: {_eq: ${'$'}levelid}}) {
      swimmerid
      firstname
      lastname
      birthday
      pfpUrl
      swimmerAbsences_aggregate(where: {absencedate: {_eq: ${'$'}absencedate}}) {
        aggregate {
          count(columns: entityid)
        }
      }
      totalAbsences: swimmerAbsences_aggregate {
        aggregate {
          count(columns: entityid)
        }
      }
    }
    levels_by_pk(levelid: ${'$'}levelid) {
      notes(where: {notedate: {_eq: ${'$'}absencedate}}) {
        noteid
        notedate
        description
      }
      trainerid
    }
  }
"""
    const val INSERT_ABSENCES_AND_NOTE = """
    mutation InsertAbsencesAndNotes(${'$'}objects1: [absences_insert_input!]!, ${'$'}objects2: [uuid!] = [], ${'$'}absencedate: date!, ${'$'}levelid: uuid!, ${'$'}trainerid: uuid!, ${'$'}description: String!) {
      insert_absences(objects: ${'$'}objects1, on_conflict: {constraint: absencedate_unique, update_columns:[]}) {
        affected_rows
      }
      delete_absences(where: {entityid: {_in: ${'$'}objects2}, absencedate: {_eq: ${'$'}absencedate}}) {
        affected_rows
      }
      insert_notes_one(object: {notedate: ${'$'}absencedate, levelid: ${'$'}levelid, description: ${'$'}description, trainerid: ${'$'}trainerid}, on_conflict: {constraint: notes_unique, update_columns: description}) {
        noteid
        notedate
        description
      }
    }
    """
    const val GET_ALL_COMPETITION = """
   
    """
    const val INSERT_COMPETITION = """
   
    """
    const val GET_TRAINER_SWIMMERS = """
    query GetTrainerSwimmers {
      levels {
        swimmers {
          swimmerid
          firstname
          lastname
        }
      }
    }
    """
}