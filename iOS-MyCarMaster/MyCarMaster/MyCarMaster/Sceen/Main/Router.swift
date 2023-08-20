//
//  Router.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/20.
//

import Combine
import UIKit

final class Router {
    private let currentStepSubject: CurrentValueSubject<Step, Never>
    private var currentStep: Step {
        return currentStepSubject.value
    }
    lazy var currentStepPublisher = currentStepSubject
        .share()
        .eraseToAnyPublisher()

    init(entryStep: Step) {
        currentStepSubject = CurrentValueSubject(entryStep)
    }

    func nextStep() {
        guard let nextStep = currentStep.next else {
            fatalError("프로그래밍 오류: 다음 스텝이 존재하지 않습니다.")
        }
        currentStepSubject.send(nextStep)
    }

    func backStep() {
        guard let backStep = currentStep.back else {
            fatalError("프로그래밍 오류: 이전 스텝이 존재하지 않습니다.")
        }
        currentStepSubject.send(backStep)
    }
}
