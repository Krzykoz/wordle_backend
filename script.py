import psycopg2
from configparser import ConfigParser

def connect(lines):

    # Establish connection to the PostgreSQL database server
    conn = psycopg2.connect(
        host="localhost",
        database="wordlydb",
        user="admin",
        password="password",
        port="5432")

    try:
        # Create a cursor object for executing SQL commands
        cur = conn.cursor()

        # SQL query to insert new rows into the Words table
        insert_query = "INSERT INTO Words (id, word, language_id) VALUES (%s, %s, %s)"

        # SQL query to retrieve the ID of the latest row in the Words table
        select_query = "SELECT id FROM Words WHERE id=(SELECT max(id) FROM Words)"
        cur.execute(select_query)
        temp = cur.fetchall()[0][0]

        # Loop through each line in the input text file and insert a new row into the Words table for each word
        for word in lines:
            temp += 1
            cur.execute(insert_query, (temp, word, 1))

        # Commit the changes and close the connection
        conn.commit()
        cur.close()
        conn.close()

    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if conn is not None:
            conn.close()
            print('Database connection closed.')

# Read the words from a text file and call the connect function to insert them into the database
with open("words.txt") as f:
    lines = f.read().splitlines()
f.close()
connect(lines)
print('Added ',len(lines),' words to database')
