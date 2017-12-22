import os
import struct
import numpy as np
import gzip
import sys

import matplotlib.pyplot as plt

from scipy.special import expit

from sklearn import metrics
from sklearn.preprocessing import Normalizer
from sklearn.decomposition import PCA
from sklearn.neural_network import MLPClassifier
from sklearn.pipeline import Pipeline
from sklearn.model_selection import GridSearchCV
from sklearn.model_selection import StratifiedKFold

# Load MNIST train data 
def load_mnist(path, kind, row):
    if (kind == 'labels'):
        labels_path = os.path.join(path)

        with gzip.open(labels_path, 'rb') as lbpath:
            lbpath.read(8)
            buffer = lbpath.read()
            labels = np.frombuffer(buffer, dtype=np.uint8)
        
        return labels

    if (kind == 'images'):
        images_path = os.path.join(path)

    
        with gzip.open(images_path, 'rb') as imgpath:
            imgpath.read(16)
            buffer = imgpath.read()
            images = np.frombuffer(buffer, dtype=np.uint8).reshape(row, 784).astype(np.float64)

        return images

# Load Data
if (len(sys.argv) == 4):
    X_train = load_mnist(sys.argv[1], 'images', 60000)
    y_train = load_mnist(sys.argv[2], 'labels', 60000)
    X_test = load_mnist(sys.argv[3], 'images', 60000)
elif (len(sys.argv) == 5):
    X_train = load_mnist(sys.argv[1], 'images', 60000)
    y_train = load_mnist(sys.argv[2], 'labels', 60000)
    X_test = load_mnist(sys.argv[3], 'images', 10000)
    y_test = load_mnist(sys.argv[4], 'labels', 10000)

# Neural Network
nn = MLPClassifier(hidden_layer_sizes=(800,),
                    activation="relu",
                    solver='adam',
                    alpha=0.0001,
                    batch_size='auto',
                    learning_rate_init=0.001,
                    max_iter=100,
                    shuffle=True,
                    random_state=1,
                    verbose=False)

# Pipeline
pipe_nn = Pipeline([('nrm', Normalizer()),
                    ('pca', PCA(n_components=250)),
                    ('clf', nn)])
pipe_nn.fit(X_train, y_train)

# Saving result
pred = pipe_nn.predict(X_test)
file = open('prediction.txt', 'w')
for i in pred:
    file.write('|{}\n'.format(pred[i]))
file.close()

if (len(sys.argv) == 5):
    print('%.2f' % (pipe_nn.score(X_train, y_train) * 100))
    print('%.2f' % (pipe_nn.score(X_test, y_test) * 100))
elif (len(sys.argv) == 4):
    for i in pred:
        print('|{}'.format(pred[i]))
