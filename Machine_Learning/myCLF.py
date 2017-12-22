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



def load_mnist(path, kind):
    labels_path = os.path.join(path, '%s-labels-idx1-ubyte.gz' % kind)
    images_path = os.path.join(path, '%s-images-idx3-ubyte.gz' % kind)

    with gzip.open(labels_path, 'rb') as lbpath:
        lbpath.read(8)
        buffer = lbpath.read()
        labels = np.frombuffer(buffer, dtype=np.uint8)

    with gzip.open(images_path, 'rb') as imgpath:
        imgpath.read(16)
        buffer = imgpath.read()
        images = np.frombuffer(buffer, dtype=np.uint8).reshape(len(labels), 784).astype(np.float64)

    return images, labels

# Load Data
X_train, y_train = load_mnist('mnist/', 'train')
X_test, y_test = load_mnist('mnist/', 'test')

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

score = pipe_nn.score(X_test, y_test)
print('Accuracy: %.2f' % (score * 100))