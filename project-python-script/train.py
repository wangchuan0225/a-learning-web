import cv2
import numpy as np

def train_faces():
    bigList = []
    faceCascade = cv2.CascadeClassifier('haarcascade_frontalface_alt.xml')

    for i in range(1, 4):
        for j in range(1, 10):
            tempList = []
            image = cv2.imread(r'E:\znsb\traindata\train' + str(i) + r'\000' + str(j) + '.jpg', 0)
            faces = faceCascade.detectMultiScale(image, 1.1, 5)

            for (x, y, w, h) in faces:
                cutResize = cv2.resize(image[y:y + h, x:x + w], (128, 128), interpolation=cv2.INTER_CUBIC)

                for x_coord in range(cutResize.shape[0]):
                    for y_coord in range(cutResize.shape[1]):
                        tempList.append(cutResize[x_coord, y_coord])

            bigList.append(tempList)

    # 将 bigList 转换为 NumPy 数组
    trainFaceMat = np.mat(bigList)

    meanFaceMat = np.mean(trainFaceMat, axis=0)

    normTrainFaceMat = trainFaceMat - meanFaceMat  # 去中心化

    covariance = np.cov(normTrainFaceMat)
    # 求得协方差矩阵的特征值和特征向量
    eigenvalue, featurevector = np.linalg.eig(covariance)  # 计算特征值和特征向量
    sorted_Index = np.argsort(eigenvalue)  # 按照特征向量进行排序后
    topk_evecs = featurevector[:, sorted_Index[:-50 - 1:-1]]
    eigenface = np.dot(np.transpose(normTrainFaceMat), topk_evecs)
    # 根据特征脸向量获取特征脸空间
    eigen_train_sample = np.dot(normTrainFaceMat, eigenface)

    # 保存训练结果
    np.savez("face_recognition_model.npz", eigenface=eigenface, meanFaceMat=meanFaceMat, eigen_train_sample=eigen_train_sample)

    print("Training completed.")

if __name__ == "__main__":
    train_faces()
