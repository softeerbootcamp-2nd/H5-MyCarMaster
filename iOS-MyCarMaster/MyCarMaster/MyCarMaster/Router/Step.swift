//
//  Step.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/20.
//

import Foundation

enum Step: Int, CaseIterable {
    case trim = 1
    case engine
    case wheelDrive
    case bodyType
    case exterior
    case interior
    case option
    case quotation
}

extension Step {
    var title: String {
        switch self {
        case .trim:
            return "트림 선택"
        case .engine:
            return "엔진 종류"
        case .wheelDrive:
            return "구동 방식"
        case .bodyType:
            return "바디 타입"
        case .exterior:
            return "외장 색상"
        case .interior:
            return "내장 색상"
        case .option:
            return "추가 옵션"
        case .quotation:
            return "견적서 완성"
        }
    }

    var next: Step? {
        switch self {
        case .trim:
            return .engine
        case .engine:
            return .wheelDrive
        case .wheelDrive:
            return .bodyType
        case .bodyType:
            return .exterior
        case .exterior:
            return .interior
        case .interior:
            return .option
        case .option:
            return .quotation
        case.quotation:
            return nil
        }
    }

    var back: Step? {
        switch self {
        case .trim:
            return nil
        case .engine:
            return .trim
        case .wheelDrive:
            return .engine
        case .bodyType:
            return .wheelDrive
        case .exterior:
            return .bodyType
        case .interior:
            return .exterior
        case .option:
            return .interior
        case .quotation:
            return .option
        }
    }

    var leftButtonTitle: String? {
        switch self {
        case .trim:
            return nil
        case .quotation:
            return "공유하기"
        default:
            return "이전"
        }
    }

    var rightButtonTitle: String? {
        switch self {
        case .quotation:
            return "카마스터 찾기"
        case .option:
            return "견적서 완성"
        default:
            return "다음"
        }
    }

    var progress: Float {
        return Float(self.rawValue) / Float(Self.allCases.count)
    }
}
