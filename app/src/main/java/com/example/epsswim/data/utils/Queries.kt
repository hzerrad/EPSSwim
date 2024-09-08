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
      swimmers_by_pk(swimmerid: ${'$'}swimmerid) {
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
        swimmerAbsences {
          absenceid
          absencedate
        }
      }
      competitionswimmers(where: {swimmerid: {_eq: ${'$'}swimmerid}}) {
        competition {
          competitionid
          competitiondate
          event
          isbrevet
          location
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
   query GetCompetitions {
     competitions {
       competitionid
       event
       competitiondate
       isbrevet
       location
       participants {
         swimmer {
           swimmerid
           firstname
           lastname
           birthday
           pfpUrl
           level {
             levelid
             levelname
           } 
         }
       }
     }
   }
    """
    const val INSERT_COMPETITION = """
   mutation CreateCompetitionWithParticipants(${'$'}competitionData: competitions_insert_input!) {
     insert_competitions_one(object: ${'$'}competitionData) {
       event
       competitiondate
       location
       isbrevet
       levelid
       participants {
         swimmer {
           swimmerid
         }
       }
     }
   }
    """
    const val GET_TRAINER_SWIMMERS = """
    query GetTrainerSwimmers {
      levels {
        levelid
        swimmers {
          swimmerid
          firstname
          lastname
        }
      }
    }
    """
    const val GET_SWIMMING_TYPES = """
    query GetEvents {
      eventtypes {
        eventtypeid
        eventname
        distance
      }
    }
    """
    const val INSERT_PARTICIPATION = """
    mutation AddSwimmerEventRecord(${'$'}swimmerid: uuid!, ${'$'}eventtypeid: uuid!, ${'$'}competitionid: uuid!, ${'$'}laptimes: [bigint!]!) {
      insert_swimmerevents_one(object: {swimmerid: ${'$'}swimmerid, competitionid: ${'$'}competitionid, eventtypeid: ${'$'}eventtypeid, laptimes: ${'$'}laptimes}) {
        laptimes
        swimmer {
          swimmerid
          firstname
          lastname
        }
      }
    }
    """
    const val GET_PARTICIPATION = """
    query GetSwimmerRecord(${'$'}competitionid: uuid!, ${'$'}swimmerid: uuid!) {
      competitions_by_pk(competitionid: ${'$'}competitionid) {
        competitionid
        competitiondate
        event
        isbrevet
        location
      }
      swimmerevents(where: {competitionid: {_eq: ${'$'}competitionid}, swimmerid: {_eq: ${'$'}swimmerid}}) {
        laptimes
        eventtype {
          eventtypeid
          eventname
          distance
        }
      }
      swimmers_by_pk(swimmerid: ${'$'}swimmerid) {
        swimmerid
        firstname
        lastname
        birthday
        pfpUrl
        level {
          levelid
          levelname
        }
      } 
    }
    """
    const val DELETE_COMPETITION = """
    mutation DeleteCompetition(${'$'}competitionid: uuid!) {
      delete_competitionswimmers(where: {competitionid: {_eq: ${'$'}competitionid}}) {
        affected_rows
      }
      delete_swimmerevents(where: {competitionid: {_eq: ${'$'}competitionid}}) {
        affected_rows
      }
      delete_competitions_by_pk(competitionid: ${'$'}competitionid) {
        event
        competitionid
        competitiondate
      }
    }
    """
    const val UPDATE_COMPETITION = """
    mutation UpdateCompetition(${'$'}competitionid: uuid = "", ${'$'}competitiondate: date = "", ${'$'}event: String = "", ${'$'}isbrevet: Boolean = false, ${'$'}location: String = "",${'$'}levelid: uuid!, ${'$'}objects: [competitionswimmers_insert_input!] = {}) {
      update_competitions_by_pk(pk_columns: {competitionid: ${'$'}competitionid}, _set: {competitiondate: ${'$'}competitiondate, event: ${'$'}event, isbrevet: ${'$'}isbrevet, location: ${'$'}location, levelid: ${'$'}levelid}) {
        competitionid
        event
        competitiondate
        isbrevet
        location
        levelid
      }
      delete_competitionswimmers(where: {competitionid: {_eq: ${'$'}competitionid}}) {
        affected_rows
      }
      insert_competitionswimmers(objects: ${'$'}objects) {
        affected_rows
      }
    }
    """
}