# Importing necessary libraries
import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity


def best():
    df = pd.read_csv("sdcard/Download/test.csv")
    df = pd.DataFrame(df, columns=["title", "author", "rating", "genre", "description", "image"])
    # randomize genre
    unique = df["genre"].unique()
    genre = np.random.choice(unique)
    # limiting the list to the chosen genre
    data = df.loc[df['genre'] == genre]
    # sort rating from highest to lowest
    data.sort_values(by=['rating'], inplace=True, ascending=False)
    data.reset_index(level=0, inplace=True)
    # get highest rated book of the genre
    result = data.iloc[0].values.tolist()
    return result[1], result[2], result[3], result[4], result[6]


def recommend(title, genre):
    df = pd.read_csv("sdcard/Download/test.csv")
    df = pd.DataFrame(df, columns=["title", "author", "rating", "genre", "description", "image"])
    # limiting the list to the given genre
    data = df.loc[df['genre'] == genre]
    data.reset_index(level=0, inplace=True)
    # converting the index into series
    indices = pd.Series(data.index, index=data['title'])
    # converting the book description into vectors
    tf = TfidfVectorizer(analyzer='word', ngram_range=(2, 2), min_df=1, stop_words='english')
    tfidf_matrix = tf.fit_transform(data['description'].values.astype('U'))
    # cosine similarity
    sg = cosine_similarity(tfidf_matrix, tfidf_matrix)
    # index of title
    idx = indices[title]
    # pairwise similarity scores
    sig = list(enumerate(sg[idx]))
    # sort scores from highest to lowest
    sig = sorted(sig, key=lambda x: x[1], reverse=True)
    # get most similar book
    book_index = sig[1][0]
    result = data.iloc[book_index].values.tolist()
    return result[1], result[2], result[3], result[4]
