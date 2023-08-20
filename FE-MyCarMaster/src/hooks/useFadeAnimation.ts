import { useState, useEffect } from "react";

export type FadeAnimationType = {
  opacity: number;
  transition: string;
};

function useFadeAnimation(isVisible: number, duration: number = 300) {
  const [opacity, setOpacity] = useState(isVisible ? 1 : 0);

  useEffect(() => {
    if (isVisible) {
      setOpacity(1);
    } else {
      const timer = setTimeout(() => {
        setOpacity(0);
      }, duration);

      return () => clearTimeout(timer);
    }
  }, [isVisible, duration]);

  return {
    opacity,
    transition: `opacity ${duration}ms`,
  };
}

export default useFadeAnimation;
