import ctypes
import os
import sys

STD_INPUT_HANDLE = -10
STD_OUTPUT_HANDLE = -11
STD_ERROR_HANDLE = -12

# 字体颜色定义 ,关键在于颜色编码，由2位十六进制组成，分别取0~f，前一位指的是背景色，后一位指的是字体色
# 由于该函数的限制，应该是只有这16种，可以前景色与背景色组合。也可以几种颜色通过或运算组合，组合后还是在这16种颜色中

# Windows CMD命令行 字体颜色定义 text colors
BLACK = 0x00  # black.
DARKBLUE = 0x01  # dark blue.
DARKGREEN = 0x02  # dark green.
DARKSKYBLUE = 0x03  # dark skyblue.
DARKRED = 0x04  # dark red.
DARKPINK = 0x05  # dark pink.
DARKYELLOW = 0x06  # dark yellow.
DARKWHITE = 0x07  # dark white.
DARKGRAY = 0x08  # dark gray.
BLUE = 0x09  # blue.
GREEN = 0x0a  # green.
SKYBLUE = 0x0b  # skyblue.
RED = 0x0c  # red.
PINK = 0x0d  # pink.
YELLOW = 0x0e  # yellow.
WHITE = 0x0f  # white.

# get handle
std_out_handle = ctypes.windll.kernel32.GetStdHandle(STD_OUTPUT_HANDLE)


def set_cmd_text_color(color, handle=std_out_handle):
    Bool = ctypes.windll.kernel32.SetConsoleTextAttribute(handle, color)
    return Bool


# reset white
def resetColor():
    set_cmd_text_color(RED | GREEN | BLUE)


def printWithColor(str, color):
    set_cmd_text_color(color)
    sys.stdout.write(u'' + str + '\n')
    resetColor()


if __name__ == '__main__':
    printWithColor('暗蓝色文字', DARKBLUE)
    printWithColor('暗绿色文字', DARKGREEN)
    printWithColor('暗天蓝色文字', DARKSKYBLUE)
    printWithColor('暗红色文字', DARKRED)
    printWithColor('暗粉红色文字', DARKPINK)
    printWithColor('暗黄色文字', DARKYELLOW)
    printWithColor('暗白色文字', DARKWHITE)
    printWithColor('暗灰色文字', DARKGRAY)
    printWithColor('蓝色文字', BLUE)
    printWithColor('绿色文字', GREEN)
    printWithColor('天蓝色文字', SKYBLUE)
    printWithColor('红色文字', RED)
    printWithColor('粉红色文字', PINK)
    printWithColor('黄色文字', YELLOW)
    printWithColor('白色文字', WHITE)
    printWithColor('天蓝色文字', SKYBLUE)

    os.system("pause")
