import psycopg2
from configparser import ConfigParser


def connect(lines):
    """ Connect to the PostgreSQL database server """
    conn = psycopg2.connect(
        host="localhost",
        database="wordlydb",
        user="admin",
        password="password",
        port="5432")

    try:
        # read connection parameters

        # create a cursor
        cur = conn.cursor()

        # execute a statement
        query = "INSERT INTO Words (id, word, language_id) VALUES (%s, %s, %s)"

        query2 = "SELECT id FROM Words WHERE id=(SELECT max(id) FROM Words)"
        cur.execute(query2)
        temp = cur.fetchall()[0][0]
        print(temp)
        # loop through the list of words and execute the query for each word
        for word in lines:
            temp += 1
            cur.execute(query, (temp, word, 1))

        # commit the changes and close the connection
        conn.commit()
        cur.close()
        conn.close()

    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if conn is not None:
            conn.close()
            print('Database connection closed.')


with open("words.txt") as f:
    lines = f.readlines()
f.close()
connect(lines)