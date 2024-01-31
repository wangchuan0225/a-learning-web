import cv2
import numpy as np

def test_faces():
    # 读取训练结果
    loaded_data = np.load("face_recognition_model.npz")
    eigenface = loaded_data['eigenface']
    meanFaceMat = loaded_data['meanFaceMat']
    eigen_train_sample = loaded_data['eigen_train_sample']

    list = []
    faceCascade = cv2.CascadeClassifier(r'haarcascade_frontalface_alt.xml')
    # Capture the video from the webcam
    cap = cv2.VideoCapture(0)

    while True:
        # Read a new frame from the video
        ret, frame = cap.read()

        # 检查是否成功读取帧
        if not ret:
            print("Failed to capture frame. Exiting...")
            break

        # Convert the frame to grayscale
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

        faces = faceCascade.detectMultiScale(gray, 1.3, 8)

        cutResize = None  # 添加这一行，确保 cutResize 在后续代码中被定义

        for (x, y, w, h) in faces:
            cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 255, 0), 2)
            cut = gray[y:y + h, x:x + w]
            # 处理成 128 * 128 大小的人脸
            cutResize = cv2.resize(cut, (128, 128), interpolation=cv2.INTER_CUBIC)

        # 在检测到人脸的情况下才执行后续代码
        if cutResize is not None:
            for x in range(cutResize.shape[0]):
                for y in range(cutResize.shape[1]):
                    list.append(cutResize[x, y])
            testFaceMat = np.mat(list)
            normTestFaceMat = testFaceMat - meanFaceMat

            # 和计算 eigen_train_sample 相同，用规格化矩阵去点乘 eigenface 即可。
            eigen_test_sample = np.dot(normTestFaceMat, eigenface)
            # 以 eigen_train_sample[0] 与 eigen_test_sample 的欧氏距离赋值 minDistance
            minDistance = np.linalg.norm(eigen_train_sample[0] - eigen_test_sample)
            # num 记录训练集中第几个人与待识别人为同一人
            num = 3
            for i in range(1, eigen_train_sample.shape[0]):
                distance = np.linalg.norm(eigen_train_sample[i] - eigen_test_sample)
                if minDistance > distance:
                    
                    minDistance = distance
                    num = i // 9 + 1
            if num == 2:
                print("2")
                break
            list = []

        cv2.imshow('Face Detection', frame)
        if cv2.waitKey(1) == ord('q'):
            break

    cap.release()
    cv2.destroyAllWindows()


if __name__ == "__main__":
    test_faces()
