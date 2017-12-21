import os
import struct
import numpy as np
import gzip
import sys

import matplotlib.pyplot as plt

from scipy.special import expit

from sklearn import metrics
from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA
from sklearn.neural_network import MLPClassifier
from sklearn.pipeline import Pipeline
from sklearn.model_selection import GridSearchCV



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
print('=== Loading MNIST Data ===')
X_train, y_train = load_mnist('mnist/', 'train')
X_test, y_test = load_mnist('mnist/', 'test')
print('Compelte!')

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
                    verbose=True)

print('=== Fitting Classifier ===')
pipe_nn = Pipeline([('scl', StandardScaler()),
                    ('pca', PCA(n_components=250)),
                    ('clf', nn)])

pipe_nn.fit(X_train, y_train)
print('Training Compeleted!')

print('=== Testing Classifier ===')
y_train_pred = pipe_nn.predict(X_train)

if sys.version_info < (3, 0):
    acc = ((np.sum(y_train == y_train_pred, axis=0)).astype('float') /
           X_train.shape[0])
else:
    acc = np.sum(y_train == y_train_pred, axis=0) / X_train.shape[0]

print('Training Accuracy: %.2f%%' % (acc * 100))

y_test_pred = pipe_nn.predict(X_test)

if sys.version_info < (3, 0):
    acc = ((np.sum(y_test == y_test_pred, axis=0)).astype('float') /
           X_test.shape[0])
else:
    acc = np.sum(y_test == y_test_pred, axis=0) / X_test.shape[0]

print('Test Accuracy: %.2f%%' % (acc * 100))