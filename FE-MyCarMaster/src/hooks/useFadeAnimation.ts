import { useState, useEffect, CSSProperties } from "react";
import "./keyframe.css";

type AnimationProps = {
  style: CSSProperties;
  onAnimationEnd: () => void;
};

function useFadeAnimation(initial: boolean = false): [boolean, (value: boolean) => void, AnimationProps, AnimationProps] {
  const [show, setShow] = useState<boolean>(initial);
  const [isVisible, setVisible] = useState<boolean>(show);

  useEffect(() => {
    if (show) setVisible(true);
  }, [show]);

  const fromProps: AnimationProps = {
    style: { animation: `${show ? "fadeIn" : "fadeOut"} 1s` },
    onAnimationEnd: () => {
      if (!show) setVisible(false);
    },
  };

  const toProps: AnimationProps = {
    style: { animation: `${show ? "fadeOut" : "fadeIn"} 1s` },
    onAnimationEnd: () => {
      if (show) setVisible(true);
    },
  };

  return [isVisible, setShow, fromProps, toProps];
}

export default useFadeAnimation;